package com.gobus.gobusservice.service;

import com.gobus.gobusservice.dto.ReviewDto;
import com.gobus.gobusservice.dto.TicketDto;
import com.gobus.gobusservice.model.Bus;
import com.gobus.gobusservice.model.Review;
import com.gobus.gobusservice.model.Ticket;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class TicketUtils {

    public static Ticket dtoToEntity(TicketDto ticketDto) throws NoSuchAlgorithmException {
        Ticket ticket = new Ticket();
        ticket.setPayment(PaymentUtils.dtoToEntity(ticketDto.getPayment()));
        ticket.setUser(UserUtils.dtoToEntity(ticketDto.getUser()));
        ticket.setTrip(TripUtils.dtoToEntity(ticketDto.getTrip()));
        ticket.setNumberOfSeatsBooked(ticketDto.getNumberOfSeatsBooked());
        ticket.setPassengerName(ticketDto.getPassengerName());
        ticket.setPassengerPhoneNumber(ticketDto.getPassengerName());
        return ticket;
    }

    public static TicketDto entityToDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setTicketId(ticket.getTicketId());
        ticketDto.setTicketStatus(ticket.getTicketStatus());
        ticketDto.setPayment(PaymentUtils.entityToDto(ticket.getPayment()));
        ticketDto.setTrip(TripUtils.entityToDto(ticket.getTrip()));
        ticketDto.setUser(UserUtils.entityToDto(ticket.getUser()));
        ticketDto.setPassengerName(ticket.getPassengerName());
        ticketDto.setNumberOfSeatsBooked(ticket.getNumberOfSeatsBooked());
        ticketDto.setTotalPrice(ticket.getTotalPrice());
        ticketDto.setPassengerPhoneNumber(ticket.getPassengerPhoneNumber());
        return ticketDto;
    }

    public static void copyFromDto(TicketDto dto, Ticket ticket) throws NoSuchAlgorithmException {
        ticket.setPassengerPhoneNumber(dto.getPassengerPhoneNumber());
        ticket.setTrip(TripUtils.dtoToEntity(dto.getTrip()));
        ticket.setPassengerName(dto.getPassengerName());
        ticket.setUser(UserUtils.dtoToEntity(dto.getUser()));
        ticket.setPayment(PaymentUtils.dtoToEntity(dto.getPayment()));
        ticket.setNumberOfSeatsBooked(ticket.getNumberOfSeatsBooked());
    }

    public static List<TicketDto> entityToDto(List<Ticket> ticketList) {
        List<TicketDto> ticketDtoList = new ArrayList<>();
        for (Ticket ticket : ticketList) {
            ticketDtoList.add(TicketUtils.entityToDto(ticket));
        }
        return ticketDtoList;
    }
}
