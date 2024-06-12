package org.iesvdm.videoclub.Model;

import org.iesvdm.videoclub.domain.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoriaMapper {

    CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

        @Mapping(source = "id_categoria", target = "id_categoria")
    CategoriaDTO categoriaACategoriaDTO(Categoria categoria);
}