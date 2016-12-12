package co.simplon.kif.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.kif.core.model.Room;
import co.simplon.kif.core.repository.RoomRepository;

@Service
public class RoomService extends GenericService<Room, RoomRepository> {
	@Autowired
	public RoomRepository roomRepository;
}
