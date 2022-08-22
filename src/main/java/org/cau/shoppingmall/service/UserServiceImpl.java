package org.cau.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.Users.UserDto;
import org.cau.shoppingmall.dto.Users.UserForm;
import org.cau.shoppingmall.dto.item.ItemDto;
import org.cau.shoppingmall.dto.orders.OrderDto;
import org.cau.shoppingmall.entity.item.OrderedItem;
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
        userRepository.findByUser_Id(user)
                .ifPresent(u -> {
                    throw new IllegalStateException("이미 존재하는 아이디입니다.");
                });
        UserForm userData = null;

        //user 정보 저장
        User createUser = new User().builder()
                .userId(userData.getUserId())
                .password(userData.getPassword())
                .userName(userData.getUserName())
                .birthday(userData.getBirthday())
                .email(userData.getEmail())
                .address(userData.getAddress())
                .build();

        User result = userRepository.save(createUser);

        return result;
    }

    @Override
    public UserDto get(Long userId) {
        //user repository에서 userId에 해당하는 user를 가져옴

        Optional<User> findUser = userRepository.findById(userId);

        if(findUser.isPresent()) {
            UserDto userDto = UserDto.of(findUser.get());
            return userDto;
        } else {
            throw new NoSuchElementException("해당하는 사용자가 없습니다.");
        }

    }

    @Override
    public User update(Long userId, UserForm form) {

        // 존재하는 유저인지 확인
        Optional<User> findUser = userRepository.findById(userId);

        User users = null;
        if (findUser.isPresent()) {
            users = findUser.get();

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
