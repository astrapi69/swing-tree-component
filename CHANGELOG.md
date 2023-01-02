## Change log
----------------------

Version 2.2-SNAPSHOT
-------------

ADDED:

- new dependency tree-api in new minor version 1.2

CHANGED:

- update of gradle to new version 8.0-rc-1
- update of gradle-plugin dependency 'io.freefair.gradle:lombok-plugin' to new version 6.6.1
- update of gradle-plugin dependency 'com.diffplug.spotless:spotless-plugin-gradle' to new version 6.12.1
- update of gradle-plugin dependency 'com.github.ben-manes.versions.gradle.plugin' to new version 0.44.0
- update of dependency swing-base-components to new minor version 2.2
- update of dependency swing-table-components to new major version 2
- update of dependency data-api to new major version 4
- update of dependency gen-tree to new minor version 7.4
- update of dependency model-data version to new minor version 2.1
- update of dependency menu-action version to new minor version 2.4
- update of dependency silly-collection to new minor version 20.2
- update of test dependency file-worker to new minor version 11.6



Version 2.1
-------------

CHANGED:

- update of gradle-plugin dependency 'com.diffplug.spotless:spotless-plugin-gradle' to new version 6.11.0
- update of dependency swing-base-components to new minor version 2.1
- update of dependency gen-tree to new major version 7
- update of dependency model-data version to new major version 2
- update of dependency menu-actions version to new major version 2
- update of dependency swing-components to new minor version 7.2
- update of dependency icon-img-extensions to new major version 2
- update of test dependency file-worker to new minor version 11.3
- update of test dependency junit-jupiter to new minor version 5.9.1

Version 2
-------------

CHANGED:

- update of JDK to newer version 11
- update of gradle to new version 7.5.1
- update of gradle-plugin dependency 'io.freefair.gradle:lombok-plugin' to new version 6.5.1
- update of gradle-plugin dependency 'org.ajoberstar.grgit:grgit-gradle' to new version 5.0.0
- update of gradle-plugin dependency 'com.diffplug.spotless:spotless-plugin-gradle' to new version 6.10.0
- update of dependency visitor version to new major version 6
- update of dependency swing-components to new major version 7
- update of dependency miglayout-swing to new major version 11.0
- update of dependency silly-collection to new major version 20
- update of test dependency file-worker to new minor version 11.1
- update of test dependency silly-io to new minor version 2.1
- update of test dependency test-object to new minor version 7.1
- update of test dependency throwable to new minor version 2.3
- update of test dependency junit-jupiter to new minor version 5.9.0

Version 1.3
-------------

ADDED:

- new dependency io.github.astrapi69:swing-renderer in new minor version 1.1
- new dependency io.github.astrapi69:component-model in new major version 1

CHANGED:

- removed all related classes that was moved to its own repository
- update of gradle-plugin dependency 'com.diffplug.spotless:spotless-plugin-gradle' to new version 6.5.2

Version 1.2
-------------

ADDED:

- new factory class DefaultMutableTreeNodeFactory for create new DefaultMutableTreeNode objects
- new extension class DefaultMutableTreeNodeExtensions that can make copies of
  DefaultMutableTreeNode objects
- new panels with BaseTreeNode as model
- new dependency io.github.astrapi69:data-api in version 3.2.1 for the api from id generation
- new test dependency io.github.astrapi69:id-generate in version 1 for id generation
- generic id generator to the initialization methods of BaseTreeNode in factory class
  BaseTreeNodeFactory

CHANGED:

- update of gradle version to 7.4.2
- update of lombok version to new 1.18.24
- update of gradle-plugin dependency com.diffplug.spotless:spotless-plugin-gradle to new minor
  version 6.5.1
- update of gradle-plugin dependency io.freefair.gradle:lombok-plugin to new patch version 6.4.3
- update of dependency gen-tree to new minor version 6.1
- update of dependency icon-img-extensions to new minor version 1.2
- update of test dependency file-worker to new minor version 8.2
- update of test dependency silly-io to new minor version 1.9
- update of test dependency silly-collections to new minor version 18.2
- update of test dependency test-objects to new major version 6
- moved package 'io.github.astrapi69.swing.tree.content.panel' to '
  io.github.astrapi69.swing.tree.panel.content'

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
- update of gradle-plugin dependency com.github.ben-manes.versions.gradle.plugin to new version
  0.42.0
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
[keep a changelog](http://keepachangelog.com/en/1.0.0/) Don’t let your friends dump git logs into
changelogs
