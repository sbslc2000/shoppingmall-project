package org.cau.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.Users.UserDto;
import org.cau.shoppingmall.dto.Users.UserForm;
import org.cau.shoppingmall.dto.orders.OrderDto;
import org.cau.shoppingmall.entity.order.Orders;
import org.cau.shoppingmall.entity.user.User;
import org.cau.shoppingmall.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;


    @Override
    @Transactional
    public User create(UserForm user) {
        /*중복회원처리*/
        userRepository.findByUserId(user.getName())
                .ifPresent(u -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        /*전송받은 User 정보 저장*/
        User result = userRepository.save(user);

        return result;
    }

    @Override
    public UserDto get(Long userId) {
        //user repository에서 userId에 해당하는 user를 가져옴

        Optional<User> findOrder = userRepository.findById(userId);

        if(findOrder.isPresent()) {
            User users = findOrder.get();

            //if not userId same: exception
            User user = userRepository.findById(userId).orElseThrow(() ->
                    new NoSuchElementException("해당하는 사용자가 없습니다."));
        }
        UserDto result = UserDto.of(users);
        return result;

    }

    @Override
    public User update(Long userId, UserForm form) {

        // 존재하는 유저인지 확인
        Optional<User> findOrder = userRepository.findById(userId);

        if(findOrder.isPresent()) {
            User users = findOrder.get();

            //if not userId same: exception
            User user = userRepository.findById(userId).orElseThrow(() ->
                    new NoSuchElementException("해당하는 사용자가 없습니다."));
        }
        User user = userRepository.save(users);

        return user;
    }

    @Override
    public User delete(Long userId) {
        return null;
    }
}
