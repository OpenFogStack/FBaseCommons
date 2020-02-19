# FBase Commons

This project is part of the FBase research project which includes the following subprojects:
* [FBase](https://github.com/OpenFogStack/FBase): Main repository with the FBase system
* [FBaseNamingService](https://github.com/OpenFogStack/FBaseNamingService): The FBase Naming Service
* [FBaseCommons](https://github.com/OpenFogStack/FBaseCommons): Common utility classes used by FBase and the FBase Naming Service
* [FBaseExample](https://github.com/OpenFogStack/FBaseExample): Example FBase setup that uses Vagrant and VirtualBox

The combination of edge and cloud in the fog computing paradigm enables a new breed of data-intensive applications. These applications, however, have to face a number of fog-specific challenges which developers have to repetitively address for every single application.
FBase is a replication service specifically tailored to the needs of data-intensive fog applications that aims to ease or eliminate challenges caused by the highly distributed and heterogeneous environment fog applications operate in.

If you use this software in a publication, please cite it as:

### Text
Jonathan Hasenburg, Martin Grambow, David Bermbach. **Towards A Replication Service for Data-Intensive Fog Applications**. In: Proceedings of the 35th ACM Symposium on Applied Computing, Posters Track (SAC 2020). ACM 2020.

Jonathan Hasenburg, Martin Grambow, David Bermbach. **FBase: A Replication Service for Data-Intensive Fog Applications**. In: Technical Report MCC.2019.1. TU Berlin & ECDF, Mobile Cloud Computing Research Group. 2018.

### BibTeX
```
@inproceedings{paper_hasenburg_towards_fbase,
	title = {{Towards A Replication Service for Data-Intensive Fog Applications}},
	booktitle = {Proceedings of the 35th ACM Symposium on Applied Computing, Posters Track (SAC 2020)},
	publisher = {ACM},
	author = {Jonathan Hasenburg and Martin Grambow and David Bermbach},
	year = {2020}
}

@inproceedings{paper_hasenburg_towards_fbase,
	title = {{Towards A Replication Service for Data-Intensive Fog Applications}},
	booktitle = {Proceedings of the 35th ACM Symposium on Applied Computing, Posters Track (SAC 2020)},
	publisher = {ACM},
	author = {Jonathan Hasenburg and Martin Grambow and David Bermbach},
	year = {2020}
}
```

A full list of our [publications](https://www.mcc.tu-berlin.de/menue/forschung/publikationen/parameter/en/) and [prototypes](https://www.mcc.tu-berlin.de/menue/forschung/prototypes/parameter/en/) is available on our group website.

## Instructions

FBase can be build by running `mvn clean package`. Do not forget to run `mvn clean install` in the local FBaseCommons [repository](https://github.com/OpenFogStack/FBaseCommons) as it is one of the dependencies. In addition, the [Naming Service](https://github.com/OpenFogStack/FBaseNamingService) should be up and running (with the quickstart configuration).

The easiest way to startup FBase is by just using the jar file (with dependencies) produced by maven. If no argument is provided, a quickstart configuration is used. Otherwise, the only argument that can be provided is the path to a config file (that is then used instead of the quickstart config). For an example config file check out `src/main/resources/sample_config.properties.`, make sure that the initialNodeConfig of the naming service is compatible to the configuration of the first FBase node started (nodeID and encryption information), because otherwise it cannot communicate with the Naming Service. The quickstart configurations are compatible.

If you need to generate a private/public RSA key, use the `RSAHelper` class' main method.
