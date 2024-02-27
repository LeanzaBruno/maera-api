package dev.kertz.decode;

public interface NotReusable {

    void markAsUsed();

    void markAsUnused();

    boolean wasUsed();
}
