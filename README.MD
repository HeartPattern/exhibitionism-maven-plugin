# exhibitionism-maven-plugin

Exhibitionism make all class, field, and method to public and non final. 

## Usage
```xml
<repositories>
    <repository>
        <id>HeartPattern</id>
        <url>https://maven.heartpattern.kr/repository/maven-public/</url>
    </repository>
</repositories>
```

```xml
<build>
    <plugins>
        <plugin>
            <groupId>kr.heartpattern</groupId>
            <artifactId>exhibitionism-maven-plugin</artifactId>
            <version>1.0.0-SNAPSHOT</version>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>exhibit</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

## Configurations
### output
Specify output file. If not configured, replace origianal file.

## parallel (Default=1)
Number of thread used to transform class. By default, use single thread

## open (Default=true)
Whether make non final.

## publify (Default=true)
Whether make public.

## debug (Default=false)
Whether print debug message

## noStaticFinal (Default=false)
If configured to true, do not make static final field to non final.

## paths
Filter paths to apply exhibitionism. For example, paths is set to net/minecraft then all class inside package net.minecraft will be transformed by exhibitionism.

## Example configuration

```xml
<plugin>
    <groupId>kr.heartpattern</groupId>
    <artifactId>exhibitionism-maven-plugin</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>exhibit</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <output>open-all.jar</output>
        <open>true</open>
        <publify>true</publify>
        <parallel>8</parallel>
        <noStaticFinal>true</noStaticFinal>
        <debug>false</debug>
        <paths>
            <path>net/minecraft</path>
            <path>org/bukkit/craftbukkit</path>
        </paths>
    </configuration>
</plugin>
```