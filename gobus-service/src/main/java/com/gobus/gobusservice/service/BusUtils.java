package com.gobus.gobusservice.service;

import com.gobus.gobusservice.dto.BusDto;
import com.gobus.gobusservice.model.Bus;

import java.util.ArrayList;
import java.util.List;

public class BusUtils {

    public static Bus dtoToEntity(BusDto busDto){
        Bus bus = new Bus();
        bus.setBusNumber(busDto.getBusNumber());
        bus.setBusName(busDto.getBusName());
        bus.setBusType(busDto.getBusType());
        bus.setNumberOfSeats(busDto.getNumberOfSeats());
        return bus;
    }

    public static List<BusDto> entityToDto(List<Bus> busList) {
        List<BusDto> busDtoList = new ArrayList<>();
        for (Bus bus : busList) {
            busDtoList.add(BusUtils.entityToDto(bus));
        }
        return busDtoList;
    }

    public static BusDto entityToDto(Bus bus){
        BusDto busdto = new BusDto();
        busdto.setBusId(bus.getBusId());
        busdto.setBusOwner(UserUtils.entityToDto(bus.getBusOwner()));
        busdto.setBusName(bus.getBusName());
        busdto.setBusType(bus.getBusType());
        busdto.setBusNumber(bus.getBusNumber());
        busdto.setNumberOfSeats(bus.getNumberOfSeats());
        return busdto;
    }

    public static void copyFromDto(BusDto dto, Bus bus) {
        bus.setBusName(dto.getBusName());
        bus.setBusNumber(dto.getBusNumber());
        bus.setNumberOfSeats(dto.getNumberOfSeats());
        bus.setBusType(dto.getBusType());
    }
}
