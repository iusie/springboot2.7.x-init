package top.cusie.springinit.wxmp;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts.EventType;
import me.chanjar.weixin.common.api.WxConsts.XmlMsgType;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.cusie.springinit.wxmp.handler.EventHandler;
import top.cusie.springinit.wxmp.handler.MessageHandler;
import top.cusie.springinit.wxmp.handler.SubscribeHandler;

import javax.annotation.Resource;

/**
 * 微信公众号路由
 *
 */
@RequiredArgsConstructor
@Configuration
public class WxMpMsgRouter {

    private final WxMpService wxMpService;

    private final EventHandler eventHandler;

    private final MessageHandler messageHandler;

    private final SubscribeHandler subscribeHandler;

    @Bean
    public WxMpMessageRouter getWxMsgRouter() {
        WxMpMessageRouter router = new WxMpMessageRouter(wxMpService);
        // 消息
        router.rule()
                .async(false)
                .msgType(XmlMsgType.TEXT)
                .handler(messageHandler)
                .end();
        // 关注
        router.rule()
                .async(false)
                .msgType(XmlMsgType.EVENT)
                .event(EventType.SUBSCRIBE)
                .handler(subscribeHandler)
                .end();
        // 点击按钮
        router.rule()
                .async(false)
                .msgType(XmlMsgType.EVENT)
                .event(EventType.CLICK)
                .eventKey(WxMpConstant.CLICK_MENU_KEY)
                .handler(eventHandler)
                .end();
        return router;
    }
}
