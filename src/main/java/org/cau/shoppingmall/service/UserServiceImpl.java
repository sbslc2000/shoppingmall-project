package org.cau.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.Users.*;
import org.cau.shoppingmall.entity.user.AccountData;
import org.cau.shoppingmall.entity.user.Authority;
import org.cau.shoppingmall.entity.user.ShoppingData;
import org.cau.shoppingmall.entity.user.User;
import org.cau.shoppingmall.exception.DuplicateUserIdException;
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
    public User create(UserForm userForm) throws DuplicateUserIdException{
        /*중복회원처리*/
        Optional<User> findUser = userRepository.findByUserId(userForm.getUserId());

        //중복 발생시 throw
        if(findUser.isPresent()) {
            throw new DuplicateUserIdException("이미 존재하는 사용자 아이디 입니다.");
        }

        //user 정보 저장

        //shopping data 생성 및 저장
        ShoppingData buildShoppingData = new ShoppingData().builder()
                .count(0)
                .point(0)
                .baskets(0)
                .likes(0)
                .reviews(0)
                .build();
        ShoppingData savedShoppingData = shoppingDataRepository.save(buildShoppingData);


        //account data 생성 및 저장
        AccountData buildAccountData = new AccountData().builder()
                .smsAgreement(userForm.isSmsAgreement())
                .lateDate(LocalDateTime.now())
                .registerDate(LocalDateTime.now())
                .build();

        AccountData savedAccountData = accountDataRepository.save(buildAccountData);


        //일반 회원 권한
        Authority authority = authorityRepository.findById(1L).get();

        //유저 생성
        User createUser = new User().builder()
                .userId(userForm.getUserId())
                .password(BCrypt.hashpw(userForm.getPassword(),BCrypt.gensalt()))
                .userName(userForm.getUserName())
                .birthday(userForm.getBirthdayYear()+userForm.getBirthdayMonth()+userForm.getBirthdayDay())
                .phoneNumber(userForm.getPhoneNumber())
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
