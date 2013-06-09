#
# Generated Makefile - do not edit!
#
# Edit the Makefile in the project folder instead (../Makefile). Each target
# has a -pre and a -post target defined where you can add customized code.
#
# This makefile implements configuration specific macros and targets.


# Environment
MKDIR=mkdir
CP=cp
GREP=grep
NM=nm
CCADMIN=CCadmin
RANLIB=ranlib
CC=gcc
CCC=g++
CXX=g++
FC=gfortran
AS=as

# Macros
CND_PLATFORM=GNU-Linux-x86
CND_DLIB_EXT=so
CND_CONF=Debug
CND_DISTDIR=dist
CND_BUILDDIR=build

# Include project Makefile
include Makefile

# Object Directory
OBJECTDIR=${CND_BUILDDIR}/${CND_CONF}/${CND_PLATFORM}

# Object Files
OBJECTFILES= \
	${OBJECTDIR}/com/minos/sandbox/Sandbox.o \
	${OBJECTDIR}/com/minos/sandbox/SandboxReply.o \
	${OBJECTDIR}/com/minos/sandbox/main.o \
	${OBJECTDIR}/com/minos/sandbox/utils.o


# C Compiler Flags
CFLAGS=

# CC Compiler Flags
CCFLAGS=
CXXFLAGS=

# Fortran Compiler Flags
FFLAGS=

# Assembler Flags
ASFLAGS=

# Link Libraries and Options
LDLIBSOPTIONS=

# Build Targets
.build-conf: ${BUILD_SUBPROJECTS}
	"${MAKE}"  -f nbproject/Makefile-${CND_CONF}.mk ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/cppsandbox

${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/cppsandbox: ${OBJECTFILES}
	${MKDIR} -p ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}
	${LINK.cc} -o ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/cppsandbox ${OBJECTFILES} ${LDLIBSOPTIONS}

${OBJECTDIR}/com/minos/sandbox/Sandbox.o: com/minos/sandbox/Sandbox.cpp 
	${MKDIR} -p ${OBJECTDIR}/com/minos/sandbox
	${RM} $@.d
	$(COMPILE.cc) -g -MMD -MP -MF $@.d -o ${OBJECTDIR}/com/minos/sandbox/Sandbox.o com/minos/sandbox/Sandbox.cpp

${OBJECTDIR}/com/minos/sandbox/SandboxReply.o: com/minos/sandbox/SandboxReply.cpp 
	${MKDIR} -p ${OBJECTDIR}/com/minos/sandbox
	${RM} $@.d
	$(COMPILE.cc) -g -MMD -MP -MF $@.d -o ${OBJECTDIR}/com/minos/sandbox/SandboxReply.o com/minos/sandbox/SandboxReply.cpp

${OBJECTDIR}/com/minos/sandbox/main.o: com/minos/sandbox/main.cpp 
	${MKDIR} -p ${OBJECTDIR}/com/minos/sandbox
	${RM} $@.d
	$(COMPILE.cc) -g -MMD -MP -MF $@.d -o ${OBJECTDIR}/com/minos/sandbox/main.o com/minos/sandbox/main.cpp

${OBJECTDIR}/com/minos/sandbox/utils.o: com/minos/sandbox/utils.cpp 
	${MKDIR} -p ${OBJECTDIR}/com/minos/sandbox
	${RM} $@.d
	$(COMPILE.cc) -g -MMD -MP -MF $@.d -o ${OBJECTDIR}/com/minos/sandbox/utils.o com/minos/sandbox/utils.cpp

# Subprojects
.build-subprojects:

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r ${CND_BUILDDIR}/${CND_CONF}
	${RM} ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/cppsandbox

# Subprojects
.clean-subprojects:

# Enable dependency checking
.dep.inc: .depcheck-impl

include .dep.inc
