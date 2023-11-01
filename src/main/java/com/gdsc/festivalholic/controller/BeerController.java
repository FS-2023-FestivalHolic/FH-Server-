package com.gdsc.festivalholic.controller;


import com.amazonaws.services.s3.AmazonS3Client;
import com.gdsc.festivalholic.config.response.ResponseDto;
import com.gdsc.festivalholic.config.response.ResponseUtil;
import com.gdsc.festivalholic.controller.dto.beer.BeerListResponseDto;
import com.gdsc.festivalholic.controller.dto.beer.BeerResponseDto;
import com.gdsc.festivalholic.controller.dto.beer.BeerSaveRequestDto;
import com.gdsc.festivalholic.controller.dto.beerImage.BeerImageUploadDto;
import com.gdsc.festivalholic.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/beers")
public class BeerController {


    private final BeerService beerService;


    //수제 맥주 생성 API
    @PostMapping("/create")
    public ResponseDto<Long> create(@RequestPart(value = "beerImageUploadDto") MultipartFile multipartFile,
                                    @RequestPart(value = "beerSaveRequestDto") BeerSaveRequestDto beerSaveRequestDto){
        //예외처리 넣기

        BeerImageUploadDto beerImageUploadDto = new BeerImageUploadDto(multipartFile);

        Long beerId = beerService.create(beerSaveRequestDto, beerImageUploadDto);

        return ResponseUtil.SUCCESS("맥주 정보 생성을 완료하였습니다.", beerId);
    }

    //특정 수제 맥주 조회 API
    @GetMapping("/{id}")
    public ResponseDto<BeerResponseDto> findBeerById(@PathVariable("id") Long beerId){

        BeerResponseDto beerById = beerService.findBeerById(beerId);

        return ResponseUtil.SUCCESS("맥주 정보 조회를 완료하였습니다.", beerById);

    }

    @GetMapping("")
    public ResponseDto<List<BeerListResponseDto>> findAllBeer(){

        List<BeerListResponseDto> allBeer = beerService.findAllBeer();

        return ResponseUtil.SUCCESS("맥주 정보 조회를 완료하였습니다.", allBeer);

    }


}