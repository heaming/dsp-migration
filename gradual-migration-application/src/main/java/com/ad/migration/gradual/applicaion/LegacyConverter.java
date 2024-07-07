package com.ad.migration.gradual.applicaion;

import com.ad.migration.gradual.domain.legacyad.DeletableEntity;
import com.ad.migration.gradual.domain.recentad.MigratedEntity;

public interface LegacyConverter<Legacy extends DeletableEntity, Recent extends MigratedEntity> {

    Recent convert(Legacy legacy);

}
