package com.dr7.dr7.web.controlerEmpresa;

import com.dr7.dr7.infra.repository.Entity.empresasEntity.Status;

public record Retorn(String criadoComSucesso, boolean b, Status status) {
}
