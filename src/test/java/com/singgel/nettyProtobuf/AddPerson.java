package com.singgel.nettyProtobuf;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;


import com.singgel.nettyProtobuf.protobuf.AddressBookProtos.AddressBook;
import com.singgel.nettyProtobuf.protobuf.AddressBookProtos.Person;
import com.singgel.nettyProtobuf.protobuf.AddressBookProtos.Person.PhoneNumber;
import com.singgel.nettyProtobuf.protobuf.AddressBookProtos.Person.PhoneType;

/**
 * 使用protobuf类示例:
 * 从控制台输入相关信息，然后将数据序列化到文件。
 *
 * @Author: hekuangsheng
 * @Date: 2018/11/9
 */
public class AddPerson {

    static Person PromptForAddress(BufferedReader stdin, PrintStream stdout) throws IOException {
        Person.Builder person = Person.newBuilder();

        stdout.print("Enter person ID: ");
        person.setId(Integer.valueOf(stdin.readLine()));

        stdout.print("Enter name: ");
        person.setName(stdin.readLine());

        stdout.print("Enter email address (blank for none): ");
        String email = stdin.readLine();
        if (email.length() > 0) {
            person.setEmail(email);
        }

        while (true) {
            stdout.print("Enter a phone number (or leave blank to finish): ");

            String number = stdin.readLine();
            if (number.length() == 0) {
                break;
            }

            PhoneNumber.Builder phoneNumber = PhoneNumber.newBuilder();
            phoneNumber.setNumber(number);

            stdout.print("Is this a mobile, home, or work phone? ");
            String type = stdin.readLine();
            if ("mobile".equalsIgnoreCase(type)) {
                phoneNumber.setType(PhoneType.MOBILE);
            } else if ("home".equalsIgnoreCase(type)) {
                phoneNumber.setType(PhoneType.HOME);
            } else if ("work".equalsIgnoreCase(type)) {
                phoneNumber.setType(PhoneType.WORK);
            }

            person.addPhones(phoneNumber);
        }

        return person.build();
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        /*if (args.length != 1) {
            System.err.println("Usage:  AddPerson ADDRESS_BOOK_FILE");
            System.exit(-1);
        }*/

        AddressBook.Builder addressBook = AddressBook.newBuilder();
        try {
            // 从指定文件读取数据
            addressBook.mergeFrom(new FileInputStream("./test.db"));
        } catch (FileNotFoundException e) {
            System.out.println("./test.db" + ": File not found.  Creating a new file.");
            e.printStackTrace();
        }

        addressBook.addPeople(PromptForAddress(new BufferedReader(new InputStreamReader(System.in)),
                System.out));

        FileOutputStream out = new FileOutputStream("./test.db");
        addressBook.build().writeTo(out);
    }

}
