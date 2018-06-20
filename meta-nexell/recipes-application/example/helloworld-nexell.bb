#
# This file was derived from the 'Hello World!' example recipe in the
# Yocto Project Development Manual.
#

SUMMARY = "Simple helloworld application"
SECTION = "examples"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://helloworld.c"

S = "${WORKDIR}"

do_compile() {
	     ${CC} helloworld.c -o helloworld_nexell
}

do_install() {
	     install -d ${D}${bindir}
	     install -m 0755 helloworld_nexell ${D}${bindir}
}


#NEXELL appended code
INSANE_SKIP_${PN} = "ldflags"
PACKAGE_ARCH = "${MACHINE_ARCH}"
FILES_${PN} = "${bindir}"