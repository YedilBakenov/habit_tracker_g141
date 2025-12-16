package kz.tracker.habbit_tracker.mapper;

import kz.tracker.habbit_tracker.dto.HabbitDto;
import kz.tracker.habbit_tracker.model.Habbit;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HabbitMapper {

    HabbitDto toDto(Habbit habbit);

    Habbit toModel(HabbitDto habbitDto);

    List<HabbitDto> toDtoList(List<Habbit>habbits);

    List<Habbit> toModel(List<HabbitDto> habbitDtos);
}
