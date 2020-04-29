package org.keer;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: spring-boot-study
 * @BelongsPackage: PACKAGE_NAME
 * @Author: keer
 * @CreateTime: 2020-04-25 22:36
 * @Description: 文件行数记录
 */
public class FileRowCount {
    /**
     * 指定文件夹下的指定类型的文件个数
     */
    private long fileCount = 0;
    /**
     * 指定文件下文件数量
     */
    private long allfileCount = 0;
    /**
     * 文件夹下所有匹配的行数
     */
    private long allFileRow = 0;
    /**
     * 文件夹下所有的代码行数
     */
    private long codeLines = 0;
    /**
     * 文件夹下所有的注释行数
     */
    private long commentLines = 0;
    /**
     * 文件夹下所有的空行数
     */
    private long blankLines = 0;

    /**
     * 指定文件夹
     */
    private String path;

    /**
     * 文件输出地址
     */
    private String outputPath;
    /**
     * 指定文件类型
     * “.java .py”
     */
    private String fileSuffix;

    /**
     * 要写入json文件的数据
     */
    private Map json = new HashMap();


    public FileRowCount(String path, String fileSuffix, String outputPath) {
        this.path = path;
        this.fileSuffix = fileSuffix;
        this.outputPath = outputPath;
    }

    /**
     * 计算文件夹下的行数
     *
     * @throws IOException
     */
    public void countRow() throws IOException {
        String glob = "glob:**/*." + fileSuffix;
        final PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(glob);

        Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                allfileCount++;
                if (pathMatcher.matches(path)) {
                    File file = path.toFile();
                    FileReader rd = new FileReader(file);
                    BufferedReader br = new BufferedReader(rd);
                    String line = "";
                    int rowCount = 0;
                    boolean flag = false;
                    int fileCodeLines = 0;
                    int fileCommentLines = 0;
                    int fileBlankLines = 0;
                    while ((line = br.readLine()) != null) {
                        line = line.trim();
                        if (line.matches("^[ ]*$")) { // 匹配空行
                            fileBlankLines++;
                        } else if (line.startsWith("//")) {
                            fileCommentLines++;
                        } else if (line.startsWith("/*") && !line.endsWith("*/")) {
                            fileCommentLines++;
                            flag = true;
                        } else if (line.startsWith("/*") && line.endsWith("*/")) {
                            fileCommentLines++;
                        } else if (flag == true) {
                            fileCommentLines++;
                            if (line.endsWith("*/")) {
                                flag = false;
                            }
                        } else {
                            fileCodeLines++;
                        }
                        rowCount++;
                    }
                    Map fileMap = new HashMap();
                    fileMap.put("fileCommentLines", fileCommentLines);
                    fileMap.put("fileCodeLines", fileCodeLines);
                    fileMap.put("rowCount", rowCount);
                    fileMap.put("fileBlankLines", fileBlankLines);

                    json.put(path.toString(), fileMap);

                    allFileRow = allFileRow + rowCount;
                    codeLines = codeLines + fileCodeLines;
                    commentLines = commentLines + fileCommentLines;
                    blankLines = blankLines + fileBlankLines;
                    fileCount++;
                    System.out.println("文件名称：" + path);
                    System.out.println("总行数：" + rowCount);
                    System.out.println("代码行数：" + fileCodeLines);
                    System.out.println("注释行数：" + fileCommentLines);
                    System.out.println("空行数：" + fileBlankLines);
                    br.close();
                    rd.close();
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc)
                    throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
        Map lastFileInfo = new HashMap();
        lastFileInfo.put("文件夹", path);
        lastFileInfo.put("文件总数", allfileCount);
        lastFileInfo.put("匹配文件类型", fileSuffix);
        lastFileInfo.put("匹配文件数目", fileCount);
        lastFileInfo.put("匹配文件总行数", allFileRow);
        lastFileInfo.put("匹配文件代码行数", codeLines);
        lastFileInfo.put("匹配文件注释行数", commentLines);
        lastFileInfo.put("匹配文件空行数", blankLines);
        json.put("总览", lastFileInfo);
        writeFile(json);
    }

    private void writeFile(Map map) throws IOException {
        String json = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty);
        File file = new File(this.outputPath);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        bufferedWriter.write(json);
        bufferedWriter.flush();
        bufferedWriter.close();


    }

    public long getFileCount() {
        return fileCount;
    }

    public long getAllFileRow() {
        return allFileRow;
    }

    public long getCodeLines() {
        return codeLines;
    }

    public long getCommentLines() {
        return commentLines;
    }

    public long getBlankLines() {
        return blankLines;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {

        return "文件夹：" + path + "\n" +
                "文件总数" + allfileCount + "\n" +
                "匹配文件类型=" + fileSuffix + "\n" +
                "匹配文件数量=" + fileCount + "\n" +
                "总行数=" + allFileRow + "\n" +
                "代码行数=" + codeLines + "\n" +
                "注释行数=" + commentLines + "\n" +
                "空行数=" + blankLines + "\n";
    }
}
