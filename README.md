# Cloud to Edge Container Offloading

To read about this project in more detail and find evaluations for this design refer to our paper which can be found [here](https://github.com/BrandonBae/duke-oit-container-offloading/blob/main/Cloud_to_Edge_Container_Offloading_Report.pdf)

![Figure 1: High Level Design](https://hackmd.io/_uploads/B1UvH7V8n.jpg)
##### Figure 1: High Level Design

This work explores an application that offloads cloud based containers to local edge devices such as laptops or desktops in order to reduce perceived latency for clients and increase performance for the cloud. In smaller computing clusters, it is feasible for a non-substantial amount of containers to lead to resource contention which can lead to increased latency for clients. In order to address this situation, we will explore the potential of offloading these cloud based containers to local devices. 

Figure 1 provides a high level overview of how we implemented this application. The computing cluster hosting the containers will run a resource monitoring application in order to predict when resource contention will occur. For this application we set thresholds for RAM
and CPU usage which when crossed, triggers an alert. When resource contention is detected, the application alerts clients of potential latency increases and asks if they would like to offload their container to their local
device. If the client chooses to offload, the application closes the client’s container on the cloud and redeploys the container on the client’s device with a shared file system

![](https://hackmd.io/_uploads/SyTlDXV8h.png)
##### Figure 2: Offloading Workflow
Figure 2 provides the general offloading workflow for our application:
1. **User Interfaces with ClientManager:** The user will use the startCloudContainer() operation to begin the workflow to create their cloud container.
2. **Initialize Cloud Container:** The client container manager requests the resource monitoring application to use its createNewContainer() operation over the TCP connection to create the client’s cloud container. The client container manager then returns the cloud container’s ID to the user.
3. **User Connects to Cloud Container:** The user utilizes the cloud container’s ID to ssh to the compute cluster and connect to the appropriate container.
4. **Write Container State to SFS:** As the user works on the cloud container, any changes they wish to save are written to the shared file system.
5. **Alert when Over Resource Limit:** Periodically the resource monitor polls the OSMXBean module to check current compute cluster resource usage. When the resource limit is crossed the resource monitor starts the offloading process.
7. **Query User for Offload:** The resource monitor queries the user for confirmation on whether to start the offloading process (due to the potential of losing current running applications). This is facilitated through the client manager.
8. **Kill Cloud Container:** If the user accepts the offload query, the client container manager calls the resource monitor’s killContainer() operation to kill the client’s cloud container.
9. **Start Local Container:** As the cloud container is being killed, the client container manager starts a new instance of the user’s container on the client’s local device. The shared file system is also mounted to maintain state between the containers. This operation also returns the local container’s ID to the user.
10. **User Connects to Local Container:** The user uses the returned container ID from the previous step to connect to the local container and resume their work

