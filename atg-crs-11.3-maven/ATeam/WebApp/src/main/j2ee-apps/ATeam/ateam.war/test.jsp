<dsp:page>
	<dsp:importbean bean="/com/atg/ateam/droplet/TestDroplet"/>
	
		<dsp:droplet name="TestDroplet">
			<dsp:oparam name="output">
				<dsp:getvalueof var="outdata" param="outdata"/>
				Current Session ID is <br>
				${outdata}			
			</dsp:oparam>
		</dsp:droplet>    	
</dsp:page>