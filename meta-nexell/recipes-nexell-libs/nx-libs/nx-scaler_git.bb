DESCRIPTION = "nx-scaler"

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://Makefile.am;md5=e5a392bc8627d4e7a7a28a76203e8239"

SRCREV = "79c9146d92b38e5407af675c959196c6aaaa889a"
SRC_URI = "git://git.nexell.co.kr/nexell/linux/library/nx-scaler;protocol=git;branch=nexell"

S = "${WORKDIR}/git"

PV = "NEXELL"
PR = "0.1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit autotools

#Nexell code : for /usr/include search QA issue: compile-host-path QA issue avoid
EXTRA_OECONF = " \
    '--prefix=${STAGING_DIR_HOST}' \
    '--with-extrapath=${STAGING_EXECPREFIXDIR}' \
    '--with-extrapath_lib=${STAGING_LIBDIR}' \
    '--with-extrapath_include=${STAGING_INCDIR}' \
    "

EXTRA_OEMAKE += " \
    'AM_CFLAGS=$(WARN_CFLAGS) -I./ -I${STAGING_INCDIR} ' \
    'libnx_scaler_la_LDFLAGS = -L${STAGING_LIBDIR}' \
     "

do_configure() {
    cd ${S}
    NOCONFIGURE=true ./autogen.sh
    oe_runconf
}

do_compile() {
    cd ${S}
    oe_runmake clean
    oe_runmake
}

do_install() {
    cd ${S}
    oe_runmake install DESTDIR=${D}
}

INSANE_SKIP_${PN} = "dev-so"
FILES_${PN} = "${libdir} ${includedir}"
FILES_SOLIBSDEV = ""
#ALLOW_EMPTY_${PN} = "1"
