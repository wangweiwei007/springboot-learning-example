package org.spring.springboot.dubbo;

import cn.com.yitong.inte.resource.dto.account.EleAcctQueryReq;
import cn.com.yitong.inte.resource.dto.card.BindCardReq;
import cn.com.yitong.inte.resource.dto.card.QueryBindCardReq;
import cn.com.yitong.inte.resource.service.AccountService;
import cn.com.yitong.inte.resource.service.CardService;
import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.config.annotation.Reference;
import com.fm.member.common.dto.HttpContentRes;
import com.fmFinance.creditLoan.common.data.Message;
import com.fmFinance.creditLoan.common.data.Messages;
import com.fmFinance.creditLoan.common.util.JSONUtil;
import com.fmfinance.message.dto.SendMsgBaseNoteReqDto;
import com.fmfinance.message.dto.SendMsgTemplateReqDto;
import com.fmfinance.message.interfaces.MsgMessageNotifyInterface;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.spring.springboot.model.City;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 城市 Dubbo 服务消费者
 *
 * Created by bysocket on 28/02/2017.
 */
@Component
public class CityDubboConsumerService {

   @Reference(version = "1.0.0")
    CityDubboService cityDubboService;

   public City printCity(String cityName) {
        City city = cityDubboService.findCityByName(cityName);
        System.out.println(city.toString());
        return city;
    }

}
