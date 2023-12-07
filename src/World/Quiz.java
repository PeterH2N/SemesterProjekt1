package World;

import javafx.geometry.Pos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//JLabel is a class of java Swing

    public class Quiz implements ActionListener {

        String[] questions = 	{
                "Which type of trash releases the most CO2 ?",
                "What type of trash is the most toxic for humans? ",
                "Which trash is considered not to be recycle?",
                "Which UN world goal is fighting to remove trash from the oceans?",
                "Where is plastic not found"
        };
        String[][] options = 	{
                {"Plastic bottles","Aluminium cans","Diapers","Styrofoam"},
                {"Bubble wrap","Styrofoam","Plastic bottle","PVC pipe"},
                {"PET bottles","Glass bottles","Styrofoam","Plastic bottles"},
                {"Goal 14 ","Goal 19","Goal 12","Goal 4"},
                {"In humans ","In the ocean","In the forrest","On Saturn"}
        };
        char[] answers = 		{
                'B',
                'D',
                'C',
                'A',
                'D'
        };

        char answer;
        int index;
        int correct_guesses =0;
        int total_questions = questions.length;
        int result;
        int seconds=10;

        JFrame frame = new JFrame();
        JTextField textfield = new JTextField();
        JTextArea textarea = new JTextArea();
        JButton buttonA = new JButton();
        JButton buttonB = new JButton();
        JButton buttonC = new JButton();
        JButton buttonD = new JButton();
        JLabel answer_labelA = new JLabel();
        JLabel answer_labelB = new JLabel();
        JLabel answer_labelC = new JLabel();
        JLabel answer_labelD = new JLabel();
        JLabel time_label = new JLabel();
        JLabel seconds_left = new JLabel();
        JTextField number_right = new JTextField();
        JTextField percentage = new JTextField();

        public Quiz() {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(650,650);
            frame.getContentPane().setBackground(new Color(145,178,255));
            frame.setLayout(null);
            frame.setResizable(false);

            textfield.setBounds(0,0,650,50);
            textfield.setBackground(new Color(145,178,255));
            textfield.setForeground(new Color(255,255,255));
            textfield.setFont(new Font("Ink Free",Font.BOLD,30)); // text type
            textfield.setBorder(BorderFactory.createBevelBorder(1));
            textfield.setHorizontalAlignment(JTextField.CENTER);
            textfield.setEditable(false);

            textarea.setBounds(0,50,650,50);
            textarea.setLineWrap(true);
            textarea.setWrapStyleWord(true);
            textarea.setBackground(new Color(145,178,255));
            textarea.setForeground(new Color(255,255,255));
            textarea.setFont(new Font("Ink Free",Font.BOLD,15));
            textarea.setBorder(BorderFactory.createBevelBorder(1));
            textarea.setEditable(false);

            buttonA.setBounds(0,100,100,100);
            buttonA.setFont(new Font("Ink Free",Font.BOLD,35));
            buttonA.setFocusable(false);
            buttonA.addActionListener(this);
            buttonA.setText("A");
            buttonA.setForeground(new Color(255,255,255));

            buttonB.setBounds(0,200,100,100);
            buttonB.setFont(new Font("Ink Free",Font.BOLD,35));
            buttonB.setFocusable(false);
            buttonB.addActionListener(this);
            buttonB.setText("B");
            buttonB.setForeground(new Color(255,255,255));

            buttonC.setBounds(0,300,100,100);
            buttonC.setFont(new Font("Ink Free",Font.BOLD,35));
            buttonC.setFocusable(false);
            buttonC.addActionListener(this);
            buttonC.setText("C");
            buttonC.setForeground(new Color(255,255,255));

            buttonD.setBounds(0,400,100,100);
            buttonD.setFont(new Font("Ink Free",Font.BOLD,35));
            buttonD.setFocusable(false);
            buttonD.addActionListener(this);
            buttonD.setText("D");
            buttonD.setForeground(new Color(255,255,255));

            answer_labelA.setBounds(125,100,500,100);
            answer_labelA.setBackground(new Color(145,178,255));
            answer_labelA.setForeground(new Color(255,255,255));
            answer_labelA.setFont(new Font("Ink Free",Font.PLAIN,35));

            answer_labelB.setBounds(125,200,500,100);
            answer_labelB.setBackground(new Color(145,178,255));
            answer_labelB.setForeground(new Color(255,255,255));
            answer_labelB.setFont(new Font("Ink Free",Font.PLAIN,35));

            answer_labelC.setBounds(125,300,500,100);
            answer_labelC.setBackground(new Color(145,178,255));
            answer_labelC.setForeground(new Color(255,255,255));
            answer_labelC.setFont(new Font("Ink Free",Font.PLAIN,35));

            answer_labelD.setBounds(125,400,500,100);
            answer_labelD.setBackground(new Color(145,178,255));
            answer_labelD.setForeground(new Color(255,255,255));
            answer_labelD.setFont(new Font("Ink Free",Font.PLAIN,35));




            number_right.setBounds(225,225,200,100);
            number_right.setBackground(new Color(0,102,204));
            number_right.setForeground(new Color(255,255,255));
            number_right.setFont(new Font("Ink Free",Font.BOLD,50));
            number_right.setBorder(BorderFactory.createBevelBorder(1));
            number_right.setHorizontalAlignment(JTextField.CENTER);
            number_right.setEditable(false);

            percentage.setBounds(225,325,200,100);
            percentage.setBackground(new Color(0,102,204));
            percentage.setForeground(new Color(255,255,255));
            percentage.setFont(new Font("Ink Free",Font.BOLD,50));
            percentage.setBorder(BorderFactory.createBevelBorder(1));
            percentage.setHorizontalAlignment(JTextField.CENTER);
            percentage.setEditable(false);

            frame.add(time_label);
            frame.add(answer_labelA);
            frame.add(answer_labelB);
            frame.add(answer_labelC);
            frame.add(answer_labelD);
            frame.add(buttonA);
            frame.add(buttonB);
            frame.add(buttonC);
            frame.add(buttonD);
            frame.add(textarea);
            frame.add(textfield);
            frame.setVisible(true);

            nextQuestion();
        }
        public void nextQuestion() {

            if(index>=total_questions) {
                results();
            }
            else {
                textfield.setText("Question "+(index+1));
                textarea.setText(questions[index]);
                answer_labelA.setText(options[index][0]);
                answer_labelB.setText(options[index][1]);
                answer_labelC.setText(options[index][2]);
                answer_labelD.setText(options[index][3]);

            }
        }
        @Override
        public void actionPerformed(ActionEvent e) {

            buttonA.setEnabled(false);
            buttonB.setEnabled(false);
            buttonC.setEnabled(false);
            buttonD.setEnabled(false);

            if(e.getSource()==buttonA) {
                answer= 'A';
                if(answer == answers[index]) {
                    correct_guesses++;
                }
            }
            if(e.getSource()==buttonB) {
                answer= 'B';
                if(answer == answers[index]) {
                    correct_guesses++;
                }
            }
            if(e.getSource()==buttonC) {
                answer= 'C';
                if(answer == answers[index]) {
                    correct_guesses++;
                }
            }
            if(e.getSource()==buttonD) {
                answer= 'D';
                if(answer == answers[index]) {
                    correct_guesses++;
                }
            }
            displayAnswer();
        }
        public void displayAnswer() {



            buttonA.setEnabled(false);
            buttonB.setEnabled(false);
            buttonC.setEnabled(false);
            buttonD.setEnabled(false);

            if(answers[index] != 'A')
                answer_labelA.setForeground(new Color(255,255,255));
            if(answers[index] != 'B')
                answer_labelB.setForeground(new Color(255,255,255));
            if(answers[index] != 'C')
                answer_labelC.setForeground(new Color(255,255,255));
            if(answers[index] != 'D')
                answer_labelD.setForeground(new Color(255,255,255));

            Timer pause = getTimer();
            pause.start();
        }

        private Timer getTimer() {
            Timer pause = new Timer(500, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    answer_labelA.setForeground(new Color(255,255,255));
                    answer_labelB.setForeground(new Color(255,255,255));
                    answer_labelC.setForeground(new Color(255,255,255));
                    answer_labelD.setForeground(new Color(255,255,255));

                    answer = ' ';
                    seconds=10;
                    seconds_left.setText(String.valueOf(seconds));
                    buttonA.setEnabled(true);
                    buttonB.setEnabled(true);
                    buttonC.setEnabled(true);
                    buttonD.setEnabled(true);
                    index++;
                    nextQuestion();
                }
            });
            pause.setRepeats(false);
            return pause;
        }

        public void results(){

            buttonA.setEnabled(false);
            buttonB.setEnabled(false);
            buttonC.setEnabled(false);
            buttonD.setEnabled(false);

            result = (int)((correct_guesses/(double)total_questions)*100);

            textfield.setText("Game Over!");
            textarea.setText("");
            answer_labelA.setText("         Result");
            answer_labelB.setText("");
            answer_labelC.setText("");
            answer_labelD.setText("");
            answer_labelA.setAlignmentX(frame.getAlignmentX()/2);


            number_right.setText("("+correct_guesses+"/"+total_questions+")");
            percentage.setText(result+"%");

            frame.add(number_right);
            frame.add(percentage);


        }
    }
// actionlistener læser når du trykker på den og derefter vil den starte metoden actionperform
// actionevent starter når du integere med button
