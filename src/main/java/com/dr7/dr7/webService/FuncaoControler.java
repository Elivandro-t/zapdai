package com.dr7.dr7.webService;

import com.dr7.dr7.application.services.funcaoService.FuncaoService;
import com.dr7.dr7.domain.vo.FuncaoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("funcao")
public class FuncaoControler {
    FuncaoService funcaoService;
  public FuncaoControler(FuncaoService funcaoService){
        this.funcaoService =  funcaoService;
    }
    @GetMapping("/lista")
    public ResponseEntity<Map<String, List<FuncaoDTO>>> findAll(){
      var response = funcaoService.findAll();
      return ResponseEntity.ok().body(response);
    }
}
