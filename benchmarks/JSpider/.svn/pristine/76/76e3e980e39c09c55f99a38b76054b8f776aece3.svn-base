package storage;

import net.javacoding.jspider.core.storage.memory.InMemoryStorageImpl;

public class StorageReport extends InMemoryStorageImpl {
	private static int parsedPages = 0;

	public StorageReport() {
		super();

//		// launch a thread updating parsedPages
//		new Thread() {
//			public void run() {
//				try {
//					while (true) {
//						parsedPages = resourceDAO.findAllResources().length;
//						Thread.sleep(5000);
//					}
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}.start();
	}

	public static int getParsedPage() {
		return parsedPages;
	}
}
