package org.cau.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.Users.UserDto;
import org.cau.shoppingmall.dto.Users.UserForm;
import org.cau.shoppingmall.dto.Users.UserUpdateForm;
import org.cau.shoppingmall.entity.user.AccountData;
import org.cau.shoppingmall.entity.user.Authority;
import org.cau.shoppingmall.entity.user.ShoppingData;
import org.cau.shoppingmall.entity.user.User;
import org.cau.shoppingmall.repository.AccountDataRepository;
import org.cau.shoppingmall.repository.AuthorityRepository;
import org.cau.shoppingmall.repository.ShoppingDataRepository;
import org.cau.shoppingmall.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final AccountDataRepository accountDataRepository;
    private final ShoppingDataRepository shoppingDataRepository;
    private final AuthorityRepository authorityRepository;


    @Override
    @Transactional
    public User create(UserForm userForm) {
        /*중복회원처리*/
        userRepository.findByUserId(userForm.getUserId())
                .ifPresent(u -> {
                    throw new IllegalStateException("이미 존재하는 아이디입니다.");
                });

        //user 정보 저장
        ShoppingData buildedShoppingData = new ShoppingData().builder()
                .count(0)
                .point(0)
                .baskets(0)
                .likes(0)
                .reviews(0)
                .build();

        ShoppingData savedShoppingData = shoppingDataRepository.save(buildedShoppingData);

        AccountData buildedAccountData = new AccountData().builder()
                .lateDate(LocalDateTime.now())
                .registerDate(LocalDateTime.now())
                .build();
        AccountData savedAccountData = accountDataRepository.save(buildedAccountData);

        Authority authority = authorityRepository.findById(1L).get();
        User createUser = new User().builder()
                .userId(userForm.getUserId())
                .password(BCrypt.hashpw(userForm.getPassword(),BCrypt.gensalt()))
                .userName(userForm.getUserName())
                .birthday(userForm.getBirthday())
                .email(userForm.getEmail())
                .address(userForm.getAddressCode()+":"+userForm.getAddress()+":"+userForm.getAddressDetails())
                .shoppingData(savedShoppingData)
                .accountData(savedAccountData)
                .authority(authority)
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
    public User update(Long userId, UserUpdateForm form) throws Exception {

        // 존재하는 유저인지 확인
        Optional<User> findUser = userRepository.findById(userId);

        User users = null;
        if (findUser.isPresent()) {
            users = findUser.get();

            users.update(form);
        } else {
            throw new NoSuchElementException("해당하는 사용자가 없습니다.");
        }

        return users;
    }

    @Override
    public void delete(Long userId) {

        Optional<User> findUser = userRepository.findById(userId);

        User users = null;
        if (findUser.isPresent()) {
            users = findUser.get();
            userRepository.delete(users);

        } else {
            throw new NoSuchElementException("해당하는 사용자가 없습니다.");
        }
    }
}
