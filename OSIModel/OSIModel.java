import java.util.ArrayList;

class PhysicalLayer {
    public ArrayList<String> send(String data) {
        System.out.println("Physical layer is converting data into bits...");
        ArrayList<String> bits = new ArrayList<>();
        for (char c : data.toCharArray()) {
            String binary = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
            for (char bit : binary.toCharArray()) {
                bits.add(String.valueOf(bit));
            }
        }
        System.out.println("Data successfully converted into bits: " + bits);
        return bits;
    }
}

class DataLinkLayer {
    public ArrayList<String> encapsulate(ArrayList<String> bits) {
        System.out.println("Data Link layer is encapsulating bits into frames...");
        ArrayList<String> frames = new ArrayList<>();
        int frameSize = 4;
        for (int i = 0; i < bits.size(); i += frameSize) {
            StringBuilder frame = new StringBuilder();
            for (int j = i; j < i + frameSize && j < bits.size(); j++) {
                frame.append(bits.get(j));
            }
            frames.add(frame.toString());
        }
        System.out.println("Bits successfully encapsulated into frames: " + frames);
        return frames;
    }

    public ArrayList<String> addParity(ArrayList<String> frames) {
        System.out.println("Data Link layer is adding parity bits to frames...");
        for (int i = 0; i < frames.size(); i++) {
            String frame = frames.get(i);
            int countOnes = frame.replaceAll("0", "").length();
            frames.set(i, frame + String.valueOf(countOnes % 2));
        }
        System.out.println("Parity bits successfully added to frames: " + frames);
        return frames;
    }
}

class NetworkLayer {
    public ArrayList<String> encapsulate(ArrayList<String> frames) {
        System.out.println("Network layer is encapsulating frames into packets...");
        ArrayList<String> packets = new ArrayList<>();
        int packetSize = 2;
        for (int i = 0; i < frames.size(); i += packetSize) {
            StringBuilder packet = new StringBuilder();
            for (int j = i; j < i + packetSize && j < frames.size(); j++) {
                packet.append(frames.get(j));
            }
            packets.add(packet.toString());
        }
        System.out.println("Frames successfully encapsulated into packets: " + packets);
        return packets;
    }
}

class TransportLayer {
    public ArrayList<String> encapsulate(ArrayList<String> packets) {
        System.out.println("Transport layer is encapsulating packets into segments...");
        ArrayList<String> segments = new ArrayList<>();
        for (String packet : packets) {
            segments.add(packet.substring(0, 1));
        }
        System.out.println("Packets successfully encapsulated into segments: " + segments);
        return segments;
    }
}

class SessionLayer {
    public String send(ArrayList<String> segments) {
        System.out.println("Session layer is preparing data for transmission...");
        StringBuilder data = new StringBuilder();
        for (String segment : segments) {
            data.append(segment);
        }
        System.out.println("Data prepared for transmission: " + data);
        return data.toString();
    }
}

class PresentationLayer {
    public String formatData(String data) {
        System.out.println("Presentation layer is formatting data for transmission...");
        String formattedData = data.toUpperCase();
        System.out.println("Data formatted for transmission: " + formattedData);
        return formattedData;
    }
}

class ApplicationLayer {
    public void send(String data) {
        System.out.println("Application layer is sending data to destination application...");
        System.out.println("Data successfully sent to destination application: " + data);
    }
}

public class OSIModel {
    public static void main(String[] args) {
        String message = "hello";

        //Entering into PhysicalLayer
        PhysicalLayer physicalLayer = new PhysicalLayer();
        ArrayList<String> bits = physicalLayer.send(message);

	//Entering into DataLayer
        DataLinkLayer dataLinkLayer = new DataLinkLayer();
        ArrayList<String> frames = dataLinkLayer.encapsulate(bits);
        ArrayList<String> framesWithParity = dataLinkLayer.addParity(frames);
	
	//Entering into NetworkLayer
        NetworkLayer networkLayer = new NetworkLayer();
        ArrayList<String> packets = networkLayer.encapsulate(framesWithParity);

	//Entering into TransportLayer
        TransportLayer transportLayer = new TransportLayer();
        ArrayList<String> segments = transportLayer.encapsulate(packets);

	//Entering into SessionLayer
        SessionLayer sessionLayer = new SessionLayer();
        String data = sessionLayer.send(segments);

	//Entering into PresentationLayer
        PresentationLayer presentationLayer = new PresentationLayer();
        String formattedData = presentationLayer.formatData(data);

	//Entering into ApplicationLayer
        ApplicationLayer applicationLayer = new ApplicationLayer();
        applicationLayer.send(formattedData);
    }
}
