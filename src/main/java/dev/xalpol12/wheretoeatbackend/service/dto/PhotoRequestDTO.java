package dev.xalpol12.wheretoeatbackend.service.dto;

import jakarta.validation.constraints.NotNull;

public record PhotoRequestDTO(
        String photoReference,
        int height,
        int width
) {}
