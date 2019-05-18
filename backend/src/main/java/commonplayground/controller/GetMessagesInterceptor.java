package commonplayground.controller;

import commonplayground.model.Message;
import commonplayground.model.MessageRepository;
import commonplayground.model.User;
import commonplayground.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Transactional
public class GetMessagesInterceptor implements HandlerInterceptor {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public GetMessagesInterceptor(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler) {
        //System.out.println("Pre Handle method is Calling");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) {

        //System.out.println("Post Handle method is Calling");
    }

    @Override
    public void afterCompletion
            (HttpServletRequest request, HttpServletResponse response, Object
                    handler, Exception exception) {

        Long userIDAsLong = Long.parseLong(request.getParameter("userID"));

        User userWhoWantsToGetMessages = userRepository.findAllById(userIDAsLong);

        for (Message message : userWhoWantsToGetMessages.getMessages()) {
            message.messageSeen();
            messageRepository.save(message);
        }

        System.out.println("Request and Response is completed and messages are seen");
    }
}
