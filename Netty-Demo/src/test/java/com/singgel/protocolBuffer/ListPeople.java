package com.singgel.protocolBuffer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.singgel.protocolBuffer.protobuf.AddressBookProtos.AddressBook;
import com.singgel.protocolBuffer.protobuf.AddressBookProtos.Person;
import com.singgel.protocolBuffer.protobuf.AddressBookProtos.Person.PhoneNumber;

/**
 * 从protobuf序列化文件读取数据。
 * @desc com.singgel.protocolBuffer.protobuf.ListPeople
 *
 * @Author: hekuangsheng
 * @Date: 2018/11/9
 */
public class ListPeople {

    static void Print(AddressBook addressBook) {
        for(Person p : addressBook.getPeopleList()) {
            System.out.println("Person ID: " + p.getId());
            System.out.println("  Name: " + p.getName());
            if (p.hasEmail()) {
                System.out.println("  E-mail address: " + p.getEmail());
            }

            for(PhoneNumber pn : p.getPhonesList()) {
                switch (pn.getType()) {
                    case MOBILE:
                        System.out.print("  Mobile phone #: ");
                        break;
                    case HOME:
                        System.out.print("  Home phone #: ");
                        break;
                    case WORK:
                        System.out.print("  Work phone #: ");
                        break;
                }
                System.out.println(pn.getNumber());
            }
        }
    }

    /**
     * @param args
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws IOException {
        /*if (args.length != 1) {
            System.err.println("Usage:  ListPeople ADDRESS_BOOK_FILE");
            System.exit(-1);
        }*/

        try {
            AddressBook addressBook = AddressBook.parseFrom(new FileInputStream("./test.db"));
            Print(addressBook);
        } catch (FileNotFoundException e) {
            System.out.println(args[0] + ": File not exists");
            e.printStackTrace();
        }

    }

}
