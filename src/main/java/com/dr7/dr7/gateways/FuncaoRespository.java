package com.dr7.dr7.gateways;

import com.dr7.dr7.domain.vo.FuncaoDTO;

import java.util.List;
import java.util.Map;

public interface FuncaoRespository {
  Map<String,List<FuncaoDTO>> findAll();
}
