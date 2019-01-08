#!/bin/bash
# Color settings
COLOR='\033[0;32m'
NC='\033[0m'

echo "This script installs an apache2 and subversion to your server"
echo "Don't run it on a productive system, this a simple and fast"
echo "setup script only. For example, it overrides the dav_svn.conf"
echo "You will asked for a passwort for the first svn user during"
echo "during installation."
echo "Currently only Ubuntu versions are supported"
echo "Be warned and proceed at your own risk..."
echo "";

#apt install openjdk-8-jdk ant

# Install apache, svn
function F_installAll() {
	echo ""; echo -e ">${COLOR} Installing ${NC}"
	apt install apache2 libapache2-mod-svn apache2-utils subversion subversion-tools
}

# configure svn and apache2 for access
# >> first parameter specifies the svn username
function F_configure() {
	echo ""; echo -e ">${COLOR} Configure ${NC}"
	# permission settings for svn
	htpasswd -c /repos/auth $1
	local SUCCESS=$?
	echo -e ">>>${COLOR} type for more users: htpasswd /repos/auth @USER ${NC}"
	echo "[groups]
	all = "$1"

	[WdSE-TTT:/]
	@all = rw
	" > /repos/access

	# modify svn module 
	echo "<Location /svn>
		DAV svn
		SVNParentPath /repos
		AuthzSVNAccessFile /repos/access
		AuthType Basic
		AuthName \"Mein SVN Repository\"
		AuthUserFile /repos/auth
		AuthBasicProvider file
		Require valid-user
	</Location>
	" >> /etc/apache2/mods-enabled/dav_svn.conf

	systemctl restart apache2 
	echo $(($? + $SUCCESS)) # exit code of command
}

#creates svn repository. 
# >> first parameter specify the repo name
function F_createRepo() {
	# create svn repo
	echo "svn repos will stored under /repos"
	mkdir /repos
	svnadmin create /repos/$1
	chown -R www-data:www-data /repos
}

# >> parameter specify the return code
function F_quit() {
	exit $1
}

function F_checkRunnable() {
	# check distribution

	if [ -f /etc/os-release ]; then
		# freedesktop.org and systemd
		. /etc/os-release
		OS=$NAME
		if [ "$OS" != "Ubuntu" ]; then
			echo "unsupported distribution"	
			F_quit 130;

		fi
	else
		echo "unsupported distribution"	
		F_quit 130;

	fi

	if [ "$EUID" -ne 0 ]
	  then echo "Please run as root"
	  F_quit 130
	fi
}

function main() {
	F_checkRunnable
	F_installAll
	read -p "Please type in your desired svn repository: " svnRepoName
	F_createRepo $svnRepoName
	read -p "Please type in your desired svn username: " svnUserName
	local SUCCESS=$(F_configure $svnUserName)
	if [[ "$SUCCESS" == "0" ]]; then
		echo "there was an error during configuring SVN"
		F_quit 130
	fi
	echo "Fin."
	echo ""; echo -e ">${COLOR} If you want to install jenkins, run install-jenkins.sh ${NC}"
}

read -p "Continue (Y/n)?" choice
case "$choice" in 
  y|Y ) echo "Running setup"; main;;
  n|N ) F_quit 0;;
  * ) echo "invalid";;
esac
