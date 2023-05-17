package g45_lexicon.teat.service;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.EventDto;
import g45_lexicon.teat.model.entity.Event;
import g45_lexicon.teat.repository.EventRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    ModelMapper modelMapper;

//    @Override
//    public List<EventDto> getAll() {
//        List<Event> eventList = eventRepository.findAllByOrOrderByIdDesc();
//        return modelMapper.map(eventList, new TypeToken<List<EventDto>>() {
//        }.getType());
//    }

    @Override
    public EventDto findById(Integer id) throws DataNotFoundException {
        if (id == null) throw new IllegalArgumentException("Event id was null");
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (!optionalEvent.isPresent()) throw new DataNotFoundException("Event id was not valid");
        Event event = optionalEvent.get();
        return modelMapper.map(event, EventDto.class);
    }

    @Override
    public EventDto findByName(String name) throws DataNotFoundException {
        if (name == null) throw new IllegalArgumentException("Event name was null");
        Optional<Event> optionalEvent = eventRepository.findByEventName(name);
        if (!optionalEvent.isPresent()) throw new DataNotFoundException("Event name was not valid");
        Event event = optionalEvent.get();
        return modelMapper.map(event, EventDto.class);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public EventDto create(EventDto eventDto) throws DataDuplicateException {
        if (eventDto == null) throw new IllegalArgumentException("Event was null");
        if (eventDto.getId() != 0) throw new IllegalArgumentException("Event id should be null or zero");
        if (eventRepository.existsById(eventDto.getId())) throw new DataDuplicateException("Event already existed");
        Event createdEntity = eventRepository.save(modelMapper.map(eventDto, Event.class));
        return modelMapper.map(createdEntity, EventDto.class);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public EventDto update(EventDto eventDto) throws DataNotFoundException, DataDuplicateException {
        if (eventDto == null) throw new IllegalArgumentException("Event data was null");
        if (eventDto.getId() == 0) throw new IllegalArgumentException("Event id should not be zero");
        if (!eventRepository.findById(eventDto.getId()).isPresent())
            throw new DataNotFoundException("Data not found error");
        if (eventRepository.findByEventName(eventDto.getEventName()).isPresent())
            throw new DataDuplicateException("Duplicate event name error");
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
