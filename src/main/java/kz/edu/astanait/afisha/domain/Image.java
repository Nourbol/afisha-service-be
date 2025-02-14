package kz.edu.astanait.afisha.domain;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public record Image(UUID id,
                    String name,
                    String type,
                    byte[] content) {

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        return object instanceof Image(UUID thatId, String thatName, String thatType, byte[] thatContent)
                && Objects.equals(this.id, thatId)
                && Objects.equals(this.name, thatName)
                && Objects.equals(this.type, thatType)
                && Arrays.equals(content, thatContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, Arrays.hashCode(content));
    }

    @Override
    public String toString() {
        return "%s[id=%s,name=%s,type=%s]".formatted(this.getClass().getSimpleName(), id, name, type);
    }
}
