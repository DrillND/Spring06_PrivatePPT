package com.mh.jpa02.service;

import com.mh.jpa02.model.Member;
import com.mh.jpa02.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MemberService implements UserDetailsService {

    @Autowired
    MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        memberRepository.findAll();
        System.out.println("로그인 버튼 누르면 일로 온다...");
        System.out.println(email);
        Member member = memberRepository.findByEmail(email);
//        System.out.println(member);
        if ( member == null)
            throw new UsernameNotFoundException(email);

//        UserDetails ud = new UserDetails() {
//            @Override
//            public Collection<? extends GrantedAuthority> getAuthorities() {
//                return null;
//            }
//
//            @Override
//            public String getPassword() {
//                return null;
//            }
//
//            @Override
//            public String getUsername() {
//                return null;
//            }
//
//            @Override
//            public boolean isAccountNonExpired() {
//                return false;
//            }
//
//            @Override
//            public boolean isAccountNonLocked() {
//                return false;
//            }
//
//            @Override
//            public boolean isCredentialsNonExpired() {
//                return false;
//            }
//
//            @Override
//            public boolean isEnabled() {
//                return false;
//            }
//        };

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
//                .password(new BCryptPasswordEncoder().encode("12341234"))
                .roles(member.getRole().toString())
                .build();
//        return User.builder()
//                .username("asdasd")
//                .password(new BCryptPasswordEncoder().encode("12341234"))
//                .roles(member.getRole().toString())
//                .build();
        // ADMIN USER
    }

    public void createMember(Member member) {
        memberRepository.save(member);  //insert 구문 실행되는부분
    }
}
