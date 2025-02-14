package kz.edu.astanait.afisha.service;

import kz.edu.astanait.afisha.domain.Image;
import java.util.UUID;

public interface EventImageReader {

    Image getCover(UUID eventId);
}
