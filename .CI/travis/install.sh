
#!/bin/bash
# Copyright (c) 2018 Shapelets.io
#
# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at http://mozilla.org/MPL/2.0/.

set -euv

if [[ "$TRAVIS_OS_NAME" == "osx" ]]; then
	# What Java versions do we have?
	/usr/libexec/java_home -V

	# Prep brew itself
	brew update
	brew outdated caskroom/cask/brew-cask || brew upgrade caskroom/cask/brew-cask

	# We must be able to get older Java versions than the latest.
	brew tap caskroom/versions
	sudo rm -rf /Library/Java/JavaVirtualMachines
	brew cask install caskroom/versions/java7

	# Fail unless we installed JDK 7 correctly.
	/usr/libexec/java_home --failfast --version 1.7
else
#    sudo apt-get install -y maven
fi