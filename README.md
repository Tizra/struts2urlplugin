# Struts2 url plugin prerelease

([Official project page](https://code.google.com/p/struts2urlplugin/))

This is a git fork of the original project, which does not seem
to be active (no changes since 2008). It's been useful to me,
but as I've had to fix some issues, I've forked it onto github.
The project page says Apache 2.0 license, so I have added license and copyright
notices to the repo, since they were not in the SVN tree I originally
checked out.

This builds on jitpack.io, for example:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.tizra</groupId>
    <artifactId>struts2urlplugin</artifactId>
    <version>0.1-tizra.1</version>
</dependency>
```

