## Change log
----------------------

Version 1.2-SNAPSHOT
-------------

ADDED:

- new factory class DefaultMutableTreeNodeFactory for create new DefaultMutableTreeNode objects
- new extension class DefaultMutableTreeNodeExtensions that can make copies of DefaultMutableTreeNode objects

CHANGED:

- update of gradle version to 7.4.1
- update of gradle-plugin dependency com.diffplug.spotless:spotless-plugin-gradle to new minor version 6.3.0
- update of gradle-plugin dependency io.freefair.gradle:lombok-plugin to new patch version 6.4.1
- update of dependency gen-tree to new minor version 5.4
- update of dependency icon-img-extensions to new minor version 1.2
- update of test dependency file-worker to new minor version 8.2
- update of test dependency silly-io to new minor version 1.9
- update of test dependency test-objects to new major version 6
- moved package 'io.github.astrapi69.swing.tree.content.panel' to 'io.github.astrapi69.swing.tree.panel.content'

Version 1.1
-------------

ADDED:

- new class GenericTreeElement created
- new class GenericTreeNodeCellRenderer created
- new class TreeNodeGenericTreeElementWithContentPanel created
- new method for get the selected user object from a given JTree object
- new method for get the selected DefaultMutableTreeNode from a given Point object
- new method for copy the selected DefaultMutableTreeNode object
- new method factory method for create DefaultMutableTreeNode object
- new test dependency for icons org.freedesktop.tango:tango-icon-theme in patch version 0.8.90
- new test dependency for icons io.github.astrapi69:silk-icon-theme in major version 1

CHANGED:

- update of gradle version to 7.3.3
- update of gradle-plugin dependency com.github.ben-manes.versions.gradle.plugin to new version 0.42.0
- update of dependency swing-base-components to new version 1.2
- update of dependency swing-table-components to new version 1.1
- update of dependency swing-components to new version 6
- moved PreferencesPanel from swing-components to this repository

Version 1
-------------

ADDED:

- new CHANGELOG.md file created
- all relevant classes from swing-components repository

Notable links:
[keep a changelog](http://keepachangelog.com/en/1.0.0/) Don’t let your friends dump git logs into changelogs
