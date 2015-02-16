package storage;

import net.javacoding.jspider.core.storage.memory.InMemoryStorageProvider;
import net.javacoding.jspider.core.storage.spi.StorageSPI;
import net.javacoding.jspider.core.util.config.PropertySet;

public class StorageReportProvider extends InMemoryStorageProvider{
	public StorageSPI createStorage(PropertySet props) {
        return new StorageReport();
    }
}
