package com.maria.service.api;

import com.maria.model.image.SaveContestLogoRequest;
import com.maria.model.image.SaveUserLogoRequest;
import com.maria.model.image.SaveContestEntryImageRequest;

/**
 * Created on 8/19/2017.
 */
public interface ImageService {
    void saveImageForContestEntry(SaveContestEntryImageRequest saveContestEntryImageRequest);

    void saveUserLogo(SaveUserLogoRequest saveUserLogoRequest);

    void saveContestLogo(SaveContestLogoRequest saveContestLogoRequest);


}
