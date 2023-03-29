package app.docker;


import app.container.EchoServer;
import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerCertificateException;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.DockerException;
import com.spotify.docker.client.messages.ContainerConfig;
import com.spotify.docker.client.messages.ContainerCreation;
import com.spotify.docker.client.messages.HostConfig;

public class DockerHandler {
    private DockerClient dockerClient;
    private String mountDirectory;
    private String imageName;
    public DockerHandler(String imageName, String containerMountDirectory) {
        try {
            dockerClient = DefaultDockerClient.fromEnv().build();
        } catch (DockerCertificateException e) {
            throw new RuntimeException(e);
        }
        this.imageName = imageName;
        this.mountDirectory = containerMountDirectory;
    }

    public String createContainer() {
        System.out.println("creating Container");
        HostConfig bindConfig = HostConfig.builder().binds(mountDirectory).build();
        ContainerConfig containerConfig = ContainerConfig.builder()
                .hostConfig(bindConfig)
                .image(imageName)
                .build();
        ContainerCreation response = null;
        try {
            response = dockerClient.createContainer(containerConfig);
            dockerClient.startContainer(response.id());
        } catch (DockerException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.id();
    }

    public void stopContainer(String containerId) {
        try {
            dockerClient.killContainer(containerId);
            dockerClient.removeContainer(containerId);
        } catch (DockerException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        DockerHandler handler = new DockerHandler("library/ubuntu", "C:\\Users\\bkunj\\ECE654\\duke-oit-container-offloading\\docker-oit-container-offloading\\src\\main\\java\\app\\docker\\test_docker");
        handler.createContainer();
    }
}
