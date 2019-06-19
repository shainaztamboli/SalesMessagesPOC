import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.poc.sales.SalesMessagesConsumer;
import com.poc.sales.cache.SalesCache;

/**
 * Test cases to validate and process sales messages
 */
public class SalesMessagesTest {

	@Before
	public void before() {
		SalesCache.clearCache();
	}

	@Test
	public void testCase1SalesMessages() throws IOException {
		BufferedReader bufferedReader = null;
		SalesMessagesConsumer consumer = new SalesMessagesConsumer();

		try {
			System.out.println();
			System.out.println("Test case #1 to process Sales Messages in TestCase1.txt:");
			File file = new File(this.getClass().getResource("/TestCase1.txt").getFile());

			bufferedReader = new BufferedReader(new FileReader(file));

			String readLine = "";

			while ((readLine = bufferedReader.readLine()) != null) {
				consumer.onMessage(readLine);
			}

			assertEquals(SalesCache.getSalesMessageCount(), 3);
			System.out.println("No. of Sales Details Report generated: " + SalesCache.getSalesMessageCount() / 10);
			assertEquals(SalesCache.getSalesMessageCount() >= 10, false);
			System.out.println("No. of Adjustments Report generated: "
					+ (SalesCache.getSalesMessageCount() >= 50 ? SalesCache.getSalesMessageCount() % 50 : 0));
			assertEquals(SalesCache.getSalesMessageCount() >= 50, false);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}
	}

	@Test
	public void testCase2SalesMessages() throws IOException {
		BufferedReader bufferedReader = null;
		SalesMessagesConsumer consumer = new SalesMessagesConsumer();

		try {
			System.out.println();
			System.out.println("Test case #2 to process Sales Messages in TestCase2.txt:");
			File file = new File(this.getClass().getResource("/TestCase2.txt").getFile());

			bufferedReader = new BufferedReader(new FileReader(file));

			String readLine = "";

			while ((readLine = bufferedReader.readLine()) != null) {
				consumer.onMessage(readLine);
			}
			assertEquals(SalesCache.getSalesMessageCount(), 13);
			System.out.println("No. of Sales Details Report generated: " + SalesCache.getSalesMessageCount() / 10);
			assertEquals(SalesCache.getSalesMessageCount() >= 10, true);
			System.out.println("No. of Adjustments Report generated: "
					+ (SalesCache.getSalesMessageCount() >= 50 ? SalesCache.getSalesMessageCount() % 50 : 0));
			assertEquals(SalesCache.getSalesMessageCount() >= 50, false);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}
	}

	@Test
	public void testCase3SalesMessages() throws IOException {
		BufferedReader bufferedReader = null;
		SalesMessagesConsumer consumer = new SalesMessagesConsumer();

		try {
			System.out.println();
			System.out.println("Test case #3 to process Sales Messages in TestCase3.txt:");
			File file = new File(this.getClass().getResource("/TestCase3.txt").getFile());

			bufferedReader = new BufferedReader(new FileReader(file));

			String readLine = "";

			while ((readLine = bufferedReader.readLine()) != null) {
				consumer.onMessage(readLine);
			}
			assertEquals(SalesCache.getSalesMessageCount(), 52);
			System.out.println("No. of Sales Details Report generated: " + SalesCache.getSalesMessageCount() / 10);
			assertEquals(SalesCache.getSalesMessageCount() >= 10, true);
			System.out.println("No. of Adjustments Report generated: "
					+ (SalesCache.getSalesMessageCount() >= 50 ? SalesCache.getSalesMessageCount() % 50 : 0));
			assertEquals(SalesCache.getSalesMessageCount() >= 50, true);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}
	}

}
