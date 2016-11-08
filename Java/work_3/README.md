# 计算java源程序中关键字的数量 #
 
## 思路： 
1. 逐字符读取程序文件，并保存在字符缓冲区中
2. 处理字符数组对给定的关键字计数
3. 忽略注释（// 和/*....*/）和字符串(".....")和单字符('...')

代码：

```java

package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by daizhen on 11/7/2016.
 */
public class KeyWordCount {

    private String filePath;
    private HashSet<String> keywordSet = new HashSet<String>();
    private ArrayList<Character> fileContent;
    private int currentProcessIndex = 0;

    public KeyWordCount(String filePath) {
        this.filePath = filePath;

        //待计数的关键字
        keywordSet.add("import");
        keywordSet.add("public");
        keywordSet.add("protected");
        keywordSet.add("private");
        keywordSet.add("package");
        keywordSet.add("class");
        keywordSet.add("new");
        keywordSet.add("try");
        keywordSet.add("catch");
        keywordSet.add("finally");
        keywordSet.add("if");
        keywordSet.add("else");
        keywordSet.add("while");
        keywordSet.add("for");
        keywordSet.add("do");
    }

    /**
     * 计数, 返回各关键字的数目
     *
     * @return
     */
    public HashMap<String, Integer> count() {
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        readFile();
        //初始化关键字的计数为0
        Iterator<String> iterator = keywordSet.iterator();
        while (iterator.hasNext()) {
            result.put(iterator.next(), 0);
        }

        while (currentProcessIndex < fileContent.size()) {
            char currentChar = fileContent.get(currentProcessIndex);

            if ((currentChar >= 'A' && currentChar <= 'Z') || (currentChar >= 'a' && currentChar <= 'z')) {
                String word = getWord();
                if (keywordSet.contains(word)) {
                    result.put(word, result.get(word) + 1);
                }
            } else if (currentChar == '/') {
                skipComments();
            } else if (currentChar == '"') {
                skipStringContent();
            } else if (currentChar == '\'') {
                skipChar();
            } else {
                currentProcessIndex++;
            }
        }

        return result;
    }

    /**
     * 读取文江
     */
    private void readFile() {
        //读取文江
        File file = new File(filePath);
        fileContent = new ArrayList<>();
        try {
            Reader reader = new InputStreamReader(new FileInputStream(file));
            int currentChar = reader.read();
            while (currentChar != -1) {
                fileContent.add((char) currentChar);
                currentChar = reader.read();
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println("Error when read file:" + ex.getMessage());
        }
    }

    /**
     * 获取连续英文字母组成的单词
     *
     * @return
     */
    private String getWord() {
        StringBuffer wordBuffer = new StringBuffer();
        char currentChar = fileContent.get(currentProcessIndex);
        while (currentProcessIndex < fileContent.size() && ((currentChar >= 'A' && currentChar <= 'Z') || (currentChar >= 'a' && currentChar <= 'z'))) {
            wordBuffer.append(currentChar);
            currentProcessIndex++;
            currentChar = fileContent.get(currentProcessIndex);
        }
        //System.out.println(wordBuffer.toString());
        return wordBuffer.toString();
    }

    /**
     * 跳过注释
     */
    private void skipComments() {
        if (fileContent.get(currentProcessIndex) == '/' && fileContent.get(currentProcessIndex + 1) == '/') {
            skipSingleLineComments();
        } else if (fileContent.get(currentProcessIndex) == '/' && fileContent.get(currentProcessIndex + 1) == '*') {
            skipMultiLineComments();
        } else {
            currentProcessIndex++;
        }
    }

    /**
     * 跳过单行注释
     */
    private void skipSingleLineComments() {

        //Skip the start '//'
        currentProcessIndex += 2;
        while (currentProcessIndex < fileContent.size()) {
            char currentChar = fileContent.get(currentProcessIndex);

            if (currentChar == '\r' || currentChar == '\n') {
                currentProcessIndex++;
                break;
            }
            currentProcessIndex++;
        }
    }

    /**
     * 跳过多行注释
     */
    private void skipMultiLineComments() {

        //Skip the start '/*'
        currentProcessIndex += 2;

        while (currentProcessIndex < fileContent.size()) {
            char currentChar = fileContent.get(currentProcessIndex);
            char preChar = fileContent.get(currentProcessIndex - 1);

            if (currentChar == '/' && preChar == '*') {
                currentProcessIndex++;
                break;
            }
            currentProcessIndex++;
        }

    }

    /**
     * 跳过字符串文本
     */
    private void skipStringContent() {
        //Skip '"'
        currentProcessIndex += 1;
        while (currentProcessIndex < fileContent.size()) {
            char currentChar = fileContent.get(currentProcessIndex);

            char preChar = fileContent.get(currentProcessIndex - 1);
            if (currentChar == '"' && preChar != '\\') {
                currentProcessIndex++;
                break;
            }
            currentProcessIndex++;
        }
    }

    private void skipChar() {
        //Skip "'"
        currentProcessIndex += 1;
        while (currentProcessIndex < fileContent.size()) {
            char currentChar = fileContent.get(currentProcessIndex);

            char preChar = fileContent.get(currentProcessIndex - 1);
            if (currentChar == '\'' && preChar != '\\') {
                currentProcessIndex++;
                break;
            }
            currentProcessIndex++;
        }
    }

	 public static void main(String[] args) {
        // write your code here
        KeyWordCount keyWordCount = new KeyWordCount("C:\\Users\\daizhen\\Downloads\\MasterCourses-master\\MasterCourses-master\\Java\\work_3\\src\\com\\test\\KeyWordCount.java");
        HashMap<String, Integer> countResult = keyWordCount.count();
        Iterator<String> iterator_Key = countResult.keySet().iterator();
        while (iterator_Key.hasNext()) {
            String key = iterator_Key.next();
            int value = countResult.get(key);

            System.out.println(key + ":" + value);
        }
    }


}


```

运行结果:
```
new:7
private:10
package:1
import:8
finally:0
for:0
do:0
while:7
public:3
protected:0
else:6
try:1
catch:1
class:1
if:10

```
