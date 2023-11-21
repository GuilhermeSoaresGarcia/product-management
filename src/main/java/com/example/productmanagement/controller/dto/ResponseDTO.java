package com.example.productmanagement.controller.dto;

public record ResponseDTO<T>(String message, T data) {
}
