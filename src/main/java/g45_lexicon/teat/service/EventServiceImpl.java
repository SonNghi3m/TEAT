package g45_lexicon.teat.service;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.EventDto;
import g45_lexicon.teat.model.entity.Event;
import g45_lexicon.teat.repository.EventRepository;
import g45_lexicon.teat.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {
    EventRepository eventRepository;
    ModelMapper modelMapper;
    UserRepository userRepository;

    @Autowired
    public EventServiceImpl (EventRepository eventRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<EventDto> getAll() {
        List<Event> list = new ArrayList<>();
        eventRepository.findAll().iterator().forEachRemaining(list::add);
        return list.stream().map(category -> modelMapper.map(category, EventDto.class)).collect(Collectors.toList());
    }

    @Override
    public EventDto findById(Integer id) throws DataNotFoundException {
        if (id == null) throw new IllegalArgumentException("Event id was null!");
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (!optionalEvent.isPresent()) throw new DataNotFoundException("Event id was not valid!");
        Event event = optionalEvent.get();
        return modelMapper.map(event, EventDto.class);
        }


    @Override
    public List<EventDto> findByName(String name) throws DataNotFoundException {
        if (name == null) throw new IllegalArgumentException("Event name was null!");
        List<Event> list = new ArrayList<>();
        eventRepository.findByEventName(name).iterator().forEachRemaining(list::add);
        return list.stream().map(category -> modelMapper.map(category, EventDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public EventDto create(EventDto eventDto) throws DataDuplicateException {
        if (eventDto == null) throw new IllegalArgumentException("Event was null!");
        if (eventDto.getId() != null) throw new IllegalArgumentException("Event id should be automatically generated!");
        if (eventDto.getStartTime().isBefore(LocalDateTime.now())) throw new IllegalArgumentException("Event start time must be after current time!");
        if (eventDto.getEndTime().isBefore(LocalDateTime.now())) throw new IllegalArgumentException("Event end time must be after current time!");
        if (eventDto.getStartTime().isAfter(eventDto.getEndTime())) throw new IllegalArgumentException("Event start time must be before event end time!");
        Event createdEntity = eventRepository.save(modelMapper.map(eventDto, Event.class));
        return modelMapper.map(createdEntity, EventDto.class);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public EventDto update(EventDto eventDto) throws DataNotFoundException, DataDuplicateException {
        if (eventDto == null) throw new IllegalArgumentException("Event data was null!");
        if (eventDto.getId() == null || eventDto.getId() == 0 ) throw new IllegalArgumentException("Event id should not be null or zero!");
        if (!eventRepository.findById(eventDto.getId()).isPresent()) throw new DataNotFoundException("Event was not found!");
        Event result = eventRepository.save(modelMapper.map(eventDto, Event.class));
        return modelMapper.map(result, EventDto.class);
    }

    @Override
    public void delete(Integer id) throws DataNotFoundException {
        EventDto eventDto = findById(id);
        if (eventDto == null) throw new DataNotFoundException("Id was not valid");
        eventRepository.deleteById(id);
    }
}
