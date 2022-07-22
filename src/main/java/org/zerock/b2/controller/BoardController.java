package org.zerock.b2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.b2.dto.*;
import org.zerock.b2.service.BoardService;

import javax.validation.Valid;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO , Model model){


        PageResponseDTO<BoardListWithImageDTO> responseDTO = boardService.list(pageRequestDTO);

        log.info(responseDTO);

        model.addAttribute("responseDTO", responseDTO);
    }

    @GetMapping("/register")
    public void registerGET(){

    }

    @PostMapping("/register") //검증하기 위해 @Valid
    public String registerPost(@Valid BoardDTO boardDTO ,
                               BindingResult bindingResult ,
                               RedirectAttributes redirectAttributes){

        log.info("board register:"+boardDTO);

        //hasErrors  = DTO에 걸어둔 @NotEmpty 중 하나라도 에러가 나면 어떻게 처리하겠다는 뜻
        if(bindingResult.hasErrors()){
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            return "redirect:/board/register";
        }

        Integer bno = boardService.register(boardDTO);

        redirectAttributes.addFlashAttribute("result",bno);

        return "redirect:/board/list";
    }

    @GetMapping("/read")
    public void read(Integer bno, PageRequestDTO pageRequestDTO , Model model){

        log.info("read"+bno);
        log.info("read"+pageRequestDTO);

        BoardDTO boardDTO = boardService.readOne(bno);

        log.info("boadrDTO"+boardDTO);

        model.addAttribute("dto",boardDTO);
    }

}
