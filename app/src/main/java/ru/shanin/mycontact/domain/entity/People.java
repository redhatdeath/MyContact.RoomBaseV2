package ru.shanin.mycontact.domain.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;

import ru.shanin.mycontact.domain.entity.comparators.ComparatorByID;
import ru.shanin.mycontact.domain.entity.comparators.ComparatorByLastFirstSecondName;

public class People {
    public static final Comparator<People> byLFN;
    public static final Comparator<People> byID;

    static {
        byLFN = new ComparatorByLastFirstSecondName();
        byID = new ComparatorByID();
    }

    private final PeopleInfo peopleInfo;
    private final String id;

    public String getId() {
        return id;
    }

    // Constructor for exist "room's database entity"
    public People(String id, PeopleInfo peopleInfo) {
        this.peopleInfo = peopleInfo;
        this.id = id;
    }

    // Constructor by default for new objects
    public People(PeopleInfo peopleInfo) {
        this.peopleInfo = peopleInfo;
        this.id = createId(peopleInfo);
    }

    private String createId(PeopleInfo peopleInfo) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No SHA256 Algorithm");
        }
        byte[] encodedHash = digest.digest(
                this.peopleInfo.getToSHA256().getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
        for (byte hash : encodedHash) {
            String hex = Integer.toHexString(0xff & hash);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @NonNull
    @Override
    public String toString() {
        return peopleInfo.toString();
    }

    public PeopleInfo getPeopleInfo() {
        return peopleInfo;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        People guest = (People) obj;
        return id.equals(guest.getId())
                && (peopleInfo == guest.getPeopleInfo()
                || (peopleInfo != null && peopleInfo.equals(guest.getPeopleInfo()))
        );
    }
}
