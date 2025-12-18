package kz.tracker.habbit_tracker.mapper;

import kz.tracker.habbit_tracker.dto.HabbitDto;
import kz.tracker.habbit_tracker.model.Habbit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HabbitMapper {

    @Mapping(source = "createdAt" , target = "addTime")
    @Mapping(source = "updatedAt" , target = "updateTime")
    HabbitDto toDto(Habbit habbit);

    @Mapping(source = "addTime" , target = "createdAt")
    @Mapping(source = "updateTime" , target = "updatedAt")
    Habbit toModel(HabbitDto habbitDto);

    List<HabbitDto> toDtoList(List<Habbit>habbits);

    List<Habbit> toModel(List<HabbitDto> habbitDtos);
}
