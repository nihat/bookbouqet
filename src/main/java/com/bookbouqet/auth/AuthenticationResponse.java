package com.bookbouqet.auth;

import lombok.Builder;

@Builder
public record AuthenticationResponse(String token) {

}
