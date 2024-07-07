package com.ad.migration.application;

import com.ad.migration.domain.legacyad.DeletableEntity;
import com.ad.migration.domain.recentad.MigratedEntity;

public interface LegacyConverter<Legacy extends DeletableEntity, Recent extends MigratedEntity> {

    Recent convert(Legacy legacy);

}
