package com.ams.kakaomessage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.*;
import net.nurigo.sdk.message.request.MultipleMessageSendingRequest;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.MultipleDetailMessageSentResponse;
import net.nurigo.sdk.message.response.MultipleMessageSentResponse;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;


@RestController
@RequestMapping("/kakao")
public class KakaoController {
	private final DefaultMessageService messageService;

    /**
     * 발급받은 API KEY와 API Secret Key를 사용해주세요.
     */
    public KakaoController() {
        this.messageService = NurigoApp.INSTANCE.initialize("NCSDB0RM174L2EJP", "PWMATMLRKNUYEHIPAYBXCE0TQWNXPQJH", "https://api.coolsms.co.kr");
    }
    /**
     * 알림톡 한건 발송 예제
     */
    @PostMapping("/send-one-ata")
    public SingleMessageSentResponse sendOneAta() {
        KakaoOption kakaoOption = new KakaoOption();
        // disableSms를 true로 설정하실 경우 문자로 대체발송 되지 않습니다.
        // kakaoOption.setDisableSms(true);

        // 등록하신 카카오 비즈니스 채널의 pfId를 입력해주세요.
        kakaoOption.setPfId("amstestid");
        // 등록하신 카카오 알림톡 템플릿의 templateId를 입력해주세요.
        kakaoOption.setTemplateId("9312496");

        // 알림톡 템플릿 내에 #{변수} 형태가 존재할 경우 variables를 설정해주세요.
        /*
        HashMap<String, String> variables = new HashMap<>();
        variables.put("#{변수명1}", "테스트");
        variables.put("#{변수명2}", "치환문구 테스트2");
        kakaoOption.setVariables(variables);
        */

        Message message = new Message();
        // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야 합니다.
        message.setFrom("01039387660");
        message.setTo("01039387660");
        message.setKakaoOptions(kakaoOption);

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);

        return response;
    }
    
	/*
	 * @PostMapping("/send-one") public SingleMessageSentResponse sendOne() {
	 * Message message = new Message(); // 발신번호 및 수신번호는 반드시 01012345678 형태로 입력되어야
	 * 합니다. message.setFrom("01039387660"); message.setTo("01032922483");
	 * message.setText("[BeMajor] 단일 메세지 전송 테스트 - 전상훈 님에게");
	 * 
	 * SingleMessageSentResponse response = this.messageService.sendOne(new
	 * SingleMessageSendingRequest(message)); System.out.println(response);
	 * 
	 * return response; }
	 */
}
