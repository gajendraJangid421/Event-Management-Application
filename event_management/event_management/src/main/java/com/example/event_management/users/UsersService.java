package com.example.event_management.users;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.IntStream;

@Component
public class UsersService {
    private static List<Users> usersList = new ArrayList<>();
    private static int usersCount = 1000;

//    static {
//        usersList.add(new UsersDetails(++usersCount, "Gajendra", "G", "J", "email", 123, "user"));
//    }
    public List<Users> findAll() {
        return usersList;
    }

    public Users getOne(int index){
        try{
            return usersList.get(index);
        }catch (Exception exception){
            System.out.println("Index not found!");
            return null;
        }
    }

    public Users getById(int id){
        Predicate<? super Users> predicate = usersList -> {
            return Objects.equals(usersList.getUsersId(), id);
        };
        return usersList.stream().filter(predicate).findFirst().orElse(null);
    }

    public Users save(Users users){
        users.setUsersId(++usersCount);
        usersList.add(users);
        return getOne(usersCount);
//        return getSingleUserByIndex(usersCount);
    }

    public void deleteById(int id){
        Predicate<Users> predicate = usersList -> Objects.equals(usersList.getUsersId(), id);

        usersList.remove(predicate);
        --usersCount;
        IntStream.range(id, usersList.size()).forEach(i -> usersList.get(i).setUsersId(i + 1));
    }


}
