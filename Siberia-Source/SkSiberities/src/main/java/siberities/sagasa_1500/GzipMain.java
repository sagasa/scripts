package siberities.sagasa_1500;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.zip.GZIPInputStream;

import ch.njol.skript.Skript;

public class GzipMain {

	public GzipMain() {
	}

	static {
		try {
			File aliasFile = new File(
					System.getProperty("user.dir") + File.separatorChar + "plugins" + File.separatorChar + "Skript"
							+ File.separatorChar + Skript.SCRIPTSFOLDER + File.separatorChar + "SAGASA",
					"FlansAliases.sk");
			if (!(aliasFile.exists())) {
				aliasFile.getParentFile().mkdirs();
				aliasFile.createNewFile();
			}
			InputStream inStream = new FileInputStream(
					System.getProperty("user.dir") + File.separatorChar + "world" + File.separatorChar + "level.dat");
			InputStream gzip = new GZIPInputStream(inStream);
			List<String> binary = new ArrayList<String>();

			int i;
			System.out.println("[SkSiberities]Modで追加されたアイテムの読み込みを開始します。");
			while ((i = gzip.read()) != -1) {
				binary.add(Integer.toHexString(i));
			}

			inStream.close();
			gzip.close();

			String textData = String.join(" ", binary);
			// 前を切る
			Pattern p = Pattern.compile("\\A.*49 74 65 6d 44 61 74 61 \\w* \\w* \\w* \\w* ");
			Matcher m = p.matcher(textData);
			String textData1 = m.replaceFirst("");
			// 後を切る
			Pattern p2 = Pattern.compile("\\w* \\w* \\w* \\w* 49 74 65 6d 41 6c 69 61 73 65 73.*\\Z");
			Matcher m2 = p2.matcher(textData1);
			String textData2 = m2.replaceFirst("end");
			// アイテムID
			Pattern p3 = Pattern.compile("\\w* 3 0 1 56 ");
			Matcher m3 = p3.matcher(textData2);
			String textData3 = m3.replaceAll("itemID ");
			// ショートネーム
			Pattern p4 = Pattern.compile("8 0 1 4b 0 \\w* \\w* ");
			Matcher m4 = p4.matcher(textData3);
			String textData4 = m4.replaceAll("");

			/*
			 * //.. 03 00 01 56 [4バイトlong型ID] 08 00 01 4B 00 .. ..
			 * [可変長アイテムショートネーム] この繰り返し
			 */
			// System.out.println(checkBeforeWritefile(aliasFile));

			// データに戻して読む
			String[] itemData = textData4.split(" ", -1);

			FileWriter filewriter = new FileWriter(aliasFile);

			// filewriter.write("こんにちは¥r¥n");
			filewriter.write("\naliases:\n");

			int i2 = 0;
			while (!itemData[i2].equals("end")) {
				long ID = 0;
				if (itemData[i2].equals("itemID")) {
					// IDなら4バイト分呼んで加算
					ID = 0;
					i2 = i2 + 1;
					ID = ID + Integer.parseInt(itemData[i2], 16) << 24;
					i2 = i2 + 1;
					ID = ID + Integer.parseInt(itemData[i2], 16) << 16;
					i2 = i2 + 1;
					ID = ID + Integer.parseInt(itemData[i2], 16) << 8;
					i2 = i2 + 1;
					ID = ID + Integer.parseInt(itemData[i2], 16);
					i2 = i2 + 1;
					// 名称取り出し

					List<Integer> itemNameByte = new ArrayList<Integer>();
					while (!itemData[i2].equals("itemID") && !itemData[i2].equals("end")) {
						itemNameByte.add((int) Integer.parseInt(itemData[i2], 16));
						i2 = i2 + 1;
					}

					byte[] array = new byte[itemNameByte.size()];
					for (int i3 = 0; i3 < array.length; i3++) {
						int d = itemNameByte.get(i3);
						array[i3] = (byte) d;
					}

					String itemName = new String(array, "UTF-8");

					Pattern p5 = Pattern.compile(":");
					Matcher m5 = p5.matcher(itemName);
					String itemName1 = m5.replaceAll(" ");

					Pattern p6 = Pattern.compile("_");
					Matcher m6 = p6.matcher(itemName1);
					String itemName2 = m6.replaceAll(" ");

					Pattern p7 = Pattern.compile("\\p{Punct}");
					Matcher m7 = p7.matcher(itemName2);
					String itemName3 = m7.replaceAll("");

					if (!itemName.contains("minecraft:")) {
						// System.out.println(itemName1+ " "+ID);

						filewriter.write("	" + itemName3 + " = " + ID + "\n");
					}
				}

			}

			filewriter.close();

			System.out.println("[SkSiberities]読み込みが完了しました。");

		} catch (PatternSyntaxException | IOException e) {
			e.printStackTrace();
		}

	}
}
